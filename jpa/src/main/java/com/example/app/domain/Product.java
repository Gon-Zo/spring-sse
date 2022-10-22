package com.example.app.domain;

import com.example.app.constract.State;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Getter
@Table
@Entity
@DynamicUpdate
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Integer totalAmount;

    @Column(nullable = false)
    private Integer currentAmount;

    @Column(nullable = false)
    private State state;

//  @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
////  @MapKey(name = "id")
//  private Set<ProductUser> productUserSet = new HashSet<>();

    @Version
    private Integer version;

    @Builder(builderClassName = "initBuilder", builderMethodName = "initBuilder")
    private Product(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    @PrePersist
    void prePersist() {
        if (null == this.totalAmount) this.totalAmount = 0;
        if (null == this.currentAmount) this.currentAmount = 0;
        this.state = State.PROGRESS;
    }

    @Transient
    public void upCurrentAmount(Integer amount) {
        this.currentAmount += amount;
    }

    @Transient
    public void soldOutState() {
        this.state = State.SOLDOUT;
    }
}
