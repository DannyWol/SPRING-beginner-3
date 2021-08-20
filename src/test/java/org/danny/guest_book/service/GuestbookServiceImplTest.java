package org.danny.guest_book.service;

import org.danny.guest_book.dto.GuestbookDTO;
import org.danny.guest_book.dto.PageRequestDTO;
import org.danny.guest_book.dto.PageResultDTO;
import org.danny.guest_book.entity.Guestbook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GuestbookServiceImplTest {

    @Autowired
    private GuestbookService service;

    @Test
    public void test_테스트_서비스_등록(){
        GuestbookDTO dto = GuestbookDTO.builder()
                .title("Sample Tile")
                .content("Sample Content")
                .writer("Sample Writer")
                .build();
        System.out.println(service.register(dto));
    }

    @Test
    public void test_getList(){
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .build();

        PageResultDTO<GuestbookDTO, Guestbook> pageResultDTO = service.getList(pageRequestDTO);

        System.out.println("PREV > " + pageResultDTO.isPrev());
        System.out.println("NEXT > " + pageResultDTO.isNext());
        System.out.println("TOTAL > " + pageResultDTO.getTotalPage());

        pageResultDTO.getDtoList().forEach(i->{
            System.out.println(i);
        });

        System.out.println("--------------");

        pageResultDTO.getPageList().forEach(i-> System.out.println(i));
    }
}