package org.danny.guest_book.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.danny.guest_book.entity.Guestbook;
import org.danny.guest_book.entity.QGuestbook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
class GuestbookRepositoryTest {

    @Autowired
    private GuestbookRepository repository;

    @Test
    public void 더미_추가(){
        IntStream.rangeClosed(1,300).forEach(i->{
            Guestbook guestbook = Guestbook.builder()
                    .title("Title.."+i)
                    .content("Cotent.."+i)
                    .writer("user.."+(i%10))
                    .build();
            System.out.println(repository.save(guestbook));
        });
    }

    @Test
    public void 업데이트시_moddate_작동_테스트(){
        Optional<Guestbook> result = repository.findById(300L);

        if(result.isPresent()){
            Guestbook guestbook = result.get();

            guestbook.changeTitle("Change Title");
            guestbook.changeContent("Change Content");

            repository.save(guestbook);
        }
    }

    @Test
    public void 단일조건_테스트_쿼리(){
        Pageable pageable = PageRequest.of(0,10,  Sort.by("gno").descending());

        QGuestbook qGuestbook = QGuestbook.guestbook;

        String keyword = "1";

        BooleanBuilder builder = new BooleanBuilder();

        BooleanExpression expression = qGuestbook.title.contains(keyword);

        builder.and(expression);

        Page<Guestbook> result = repository.findAll(builder, pageable);

        result.stream().forEach(i->{
            System.out.println(i);
        });
    }

    @Test
    public void 다중조건_테스트_쿼리(){
        Pageable pageable = PageRequest.of(0, 10, Sort.by("gno").descending());

        QGuestbook qGuestbook = QGuestbook.guestbook;

        String keyword = "1";

        BooleanBuilder builder = new BooleanBuilder();

        BooleanExpression exContent = qGuestbook.content.contains(keyword);
        BooleanExpression exTitle = qGuestbook.title.contains(keyword);

        builder.and(exTitle).and(exContent);

        Page<Guestbook> result = repository.findAll(builder, pageable);

        result.forEach(i->{
            System.out.println(i);
        });

    }
}