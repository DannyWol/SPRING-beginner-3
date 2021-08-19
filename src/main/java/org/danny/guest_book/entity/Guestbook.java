package org.danny.guest_book.entity;

import lombok.*;

import javax.persistence.*;

@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table
@Entity
public class Guestbook extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gno;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(length = 1500, nullable = false)
    private String content;

    @Column(length = 50, nullable = false)
    private String writer;
}