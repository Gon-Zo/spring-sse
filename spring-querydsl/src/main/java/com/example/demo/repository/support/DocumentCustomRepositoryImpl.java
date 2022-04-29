package com.example.demo.repository.support;

import com.example.demo.domain.Document;
import com.example.demo.domain.projection.DocumentInfo;
import com.example.demo.domain.projection.PaymentCommentInfo;
import com.example.demo.repository.support.boxbuilder.BoxBuilderFactory;
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
  public Page<DocumentInfo> findByBoxAction(Pageable pageable, BoxBuilderFactory factory) {

    JPAQuery<Document> query =
        jpaQueryFactory
            .selectFrom(document)
            .innerJoin(document.user, user)
            .innerJoin(document.division, division)
            .leftJoin(document.paymentCommentSet, paymentComment)
            .where(factory.getBoxListInWhere());

    List<DocumentInfo> result =
        query
            .transform(
                groupBy(document.id)
                    .list(
                        Projections.fields(
                            DocumentInfo.class,
                            document.id,
                            document.title,
                            document.content,
                            document.createdDate,
                            document.updatedDate,
                            document.user.email.as("writer"),
                            document.division.name.as("divisionName"),
                            document.state.as("state"),
                            document.step,
                            list(Projections.fields(
                                    PaymentCommentInfo.class,
                                    paymentComment.id.userId.as("userId"),
                                    paymentComment.user.email.as("userEmail"),
                                    paymentComment.comment,
                                    paymentComment.step,
                                    paymentComment.state))
                                .as("paymentCommentSet"))))
            .stream()
            .skip(pageable.getOffset())
            .limit(pageable.getPageSize())
            .collect(Collectors.toList());

    long total = query.fetchCount();

    return new PageImpl<>(result, pageable, total);
  }
}
