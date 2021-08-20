package org.danny.guest_book.service;

import org.danny.guest_book.dto.GuestbookDTO;
import org.danny.guest_book.dto.PageRequestDTO;
import org.danny.guest_book.dto.PageResultDTO;
import org.danny.guest_book.entity.Guestbook;
import org.springframework.data.domain.PageRequest;

public interface GuestbookService {
    Long register(GuestbookDTO dto);

    default Guestbook dtoToEntity(GuestbookDTO dto) {
        Guestbook entity = Guestbook.builder()
                .gno(dto.getGno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(dto.getWriter())
                .build();
        return entity;
    }

    default GuestbookDTO entityToDTO(Guestbook entity){
        GuestbookDTO dto = GuestbookDTO.builder()
                .gno(entity.getGno())
                .title(entity.getTitle())
                .content(entity.getContent())
                .writer(entity.getWriter())
                .modDate(entity.getModDate())
                .regDate(entity.getRegDate())
                .build();
        return dto;
    }

    PageResultDTO<GuestbookDTO, Guestbook> getList(PageRequestDTO requestDTO);

    GuestbookDTO read(Long gno);

    void remove(Long gno);

    void modify(GuestbookDTO dto);
}
