package com.example.zoomarket.controller;


import com.example.zoomarket.exp.sms.IncorrectDateFormatException;
import com.example.zoomarket.exp.sms.LimitOutPutException;
import com.example.zoomarket.exp.attach.*;
import com.example.zoomarket.exp.auth.*;
import com.example.zoomarket.exp.post.PostDeleteNotAllowedException;
import com.example.zoomarket.exp.post.PostNotFoundException;
import com.example.zoomarket.exp.post.PostUpdateNotAllowedException;
import com.example.zoomarket.exp.post.like.PostAlreadyLikedException;
import com.example.zoomarket.exp.post.type.PostTypeNotFoundException;
import com.example.zoomarket.exp.profile.ProfileNotFoundException;
import com.example.zoomarket.exp.sms.SMSHistoryNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


import java.util.*;

@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", status.value());

        List<String> errors = new LinkedList<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            errors.add(fieldError.getDefaultMessage());
        }
        body.put("errors", errors);
        return new ResponseEntity<>(body, headers, status);
    }

    @ExceptionHandler({PhoneAlreadyExistsException.class})
    private ResponseEntity<?> handler(PhoneAlreadyExistsException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler({IncorrectDateFormatException.class})
    private ResponseEntity<?> handler(IncorrectDateFormatException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler({LimitOutPutException.class})
    private ResponseEntity<?> handler(LimitOutPutException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler({LoginOrPasswordWrongException.class})
    private ResponseEntity<?> handler(LoginOrPasswordWrongException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler({ProfileStatusBlockException.class})
    private ResponseEntity<?> handler(ProfileStatusBlockException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler({CouldNotRead.class})
    private ResponseEntity<?> handler(CouldNotRead e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler({FileNotFoundException.class})
    private ResponseEntity<?> handler(FileNotFoundException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler({FileUploadException.class})
    private ResponseEntity<?> handler(FileUploadException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler({FileTypeIncorrectException.class})
    private ResponseEntity<?> handler(FileTypeIncorrectException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler({OriginalFileNameNullException.class})
    private ResponseEntity<?> handler(OriginalFileNameNullException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler({SomethingWentWrong.class})
    private ResponseEntity<?> handler(SomethingWentWrong e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler({PasswordIncorrectException.class})
    private ResponseEntity<?> handler(PasswordIncorrectException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }


    @ExceptionHandler({ProfileNotFoundException.class})
    private ResponseEntity<?> handler(ProfileNotFoundException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler({AdminCreateNotAllowedException.class})
    private ResponseEntity<?> handler(AdminCreateNotAllowedException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
    }

    @ExceptionHandler({IncorrectSMSCodeException.class})
    private ResponseEntity<?> handler(IncorrectSMSCodeException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler({PhoneNotFoundException.class})
    private ResponseEntity<?> handler(PhoneNotFoundException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler({SMSHistoryNotFoundException.class})
    private ResponseEntity<?> handler(SMSHistoryNotFoundException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler({PostTypeNotFoundException.class})
    private ResponseEntity<?> handler(PostTypeNotFoundException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler({PostAlreadyLikedException.class})
    private ResponseEntity<?> handler(PostAlreadyLikedException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler({PostNotFoundException.class})
    private ResponseEntity<?> handler(PostNotFoundException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler({PostDeleteNotAllowedException.class})
    private ResponseEntity<?> handler(PostDeleteNotAllowedException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
    }

    @ExceptionHandler({PostUpdateNotAllowedException.class})
    private ResponseEntity<?> handler(PostUpdateNotAllowedException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
    }

    @ExceptionHandler({JWTTokenExpiredException.class})
    private ResponseEntity<?> handler(JWTTokenExpiredException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
    }
}
