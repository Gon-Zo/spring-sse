package com.example.demo.repository.support;

import com.example.demo.domain.Document;
import com.example.demo.domain.projection.CommentProjection;
import com.example.demo.domain.projection.DocumentProjection;
import com.example.demo.enums.StateEnum;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.demo.domain.QDivision.division;
import static com.example.demo.domain.QDocument.document;
import static com.example.demo.domain.QPaymentComment.paymentComment;
import static com.example.demo.domain.QUser.user;
import static com.querydsl.core.group.GroupBy.groupBy;
import static com.querydsl.core.group.GroupBy.list;

public class DocumentCustomRepositoryImpl extends QuerydslRepositorySupport
    implements DocumentCustomRepository {

  private final JPAQueryFactory jpaQueryFactory;

  public DocumentCustomRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
    super(Document.class);
    this.jpaQueryFactory = jpaQueryFactory;
  }

  @Override
  public Page<DocumentProjection> findAllQuery(Pageable pageable, Long userId) {

    JPAQuery<Document> query =
        jpaQueryFactory
            .selectFrom(document)
            .innerJoin(document.user, user)
            .innerJoin(document.division, division)
            .leftJoin(document.paymentCommentSet, paymentComment)

            // active
            //            .where(document.user.id.eq(userId).or(paymentComment.user.id)
            // .and(document.state.in(StateEnum.OK, StateEnum.NO)))

            // indox;
            .where(paymentComment.id.userId.eq(userId).or(document.user.id.eq(userId)))
            .where(document.state.in(StateEnum.OK, StateEnum.NO))
        // outbox
        //            .where(document.user.id.eq(userId).and(document.state.eq(StateEnum.NONE)));
        ;

    List<DocumentProjection> result =
        query
            .transform(
                groupBy(document.id)
                    .list(
                        Projections.fields(
                            DocumentProjection.class,
                            document.id,
                            document.title,
                            document.content,
                            document.createdDate,
                            document.updatedDate,
                            document.user.email.as("writer"),
                            document.division.name.as("divisionName"),
                            document.state.as("state"),
                            list(Projections.fields(
                                    CommentProjection.class,
                                    paymentComment.id.documentId.as("documentId"),
                                    paymentComment.id.userId.as("userId")))
                                .as("paymentCommentSet"))))
            .stream()
            .skip(pageable.getOffset())
            .limit(pageable.getPageSize())
            .collect(Collectors.toList());

    long total = query.fetchCount();

    return new PageImpl<>(result, pageable, total);
  }

  @Override
  public Page<DocumentProjection> findByAllQuery2(Pageable pageable) {

    return null;
  }
}
