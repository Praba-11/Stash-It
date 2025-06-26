package files.vault.controller;

import files.vault.component.service.MemberBuilder;
import files.vault.domain.dto.MemberRequestDto;
import files.vault.domain.entity.Member;
import files.vault.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberBuilder memberBuilder;

    @PostMapping("/create")
    public ResponseEntity<Void> createMember(@RequestBody MemberRequestDto dto) {

        log.info("Member create initiated. Name: {}", dto.getFirstName());

        Member member = memberBuilder.build(dto);
        memberService.create(member);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}

