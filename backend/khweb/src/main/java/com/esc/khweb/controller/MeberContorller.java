package com.esc.khweb.controller;

import com.esc.khweb.Service.MemberService;
import com.esc.khweb.controller.request.MemberRequest;
import com.esc.khweb.entity.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/member")
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
@Slf4j
public class MeberContorller {

        @Autowired
        MemberService service;


        @PostMapping("/register")
        public ResponseEntity<Void> memberRegister(@Validated @RequestBody Member member) throws Exception {
                service.memberRegister(member);

                return new ResponseEntity<Void>(HttpStatus.OK);
        }

        @PostMapping("/FindById")
        public ResponseEntity<List> FindById (@Validated @RequestBody MemberRequest memberRequest) throws  Exception {
                String email = memberRequest.getEmail();
              List result =   service.FindById(email);

                return  new ResponseEntity<>(result,HttpStatus.OK);
        }

        @PostMapping("/FindBymemberImp")
        public  ResponseEntity<Member> FindBymemberImp (@Validated @RequestBody MemberRequest memberRequest) throws  Exception {
                try {
                        String memberId = memberRequest.getMemberId();
                        Optional<Member> member = service.FindBymemberImp(memberId);
                        Member member1 = member.get();
                        return new ResponseEntity<>(member1, HttpStatus.OK);
                }
                catch (Exception e){

                        return  new ResponseEntity<>(null,HttpStatus.OK);
                }
        }
        @PostMapping("/IdMatchedBirthday")
        public ResponseEntity<Boolean> IdMatchedBirthday (@Validated @RequestBody MemberRequest memberRequest) throws  Exception {



                Boolean result = service.IdMatchedBirthday(memberRequest);

                return new ResponseEntity<>(result,HttpStatus.OK);
        }

        @PostMapping("/ModifyPassword")
        public ResponseEntity<Void> ModifyPassword (@Validated @RequestBody Member member) throws  Exception {

                service.memberRegister(member);

                return new ResponseEntity<Void>(HttpStatus.OK);
        }

        @PostMapping("/getMemberList")
        public ResponseEntity<List<Member>> getMeberList () throws  Exception {

                List<Member> list = service.getMeberList();

                return new ResponseEntity<>(list,HttpStatus.OK);
        }



}