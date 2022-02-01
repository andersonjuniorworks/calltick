package com.andersonjunior.calltick.dto;

import java.io.Serializable;
import java.util.Date;

import com.andersonjunior.calltick.models.Called;
import com.andersonjunior.calltick.models.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommentDto implements Serializable {

    private Long id;

    private String content;

    private Called called;
    
    private User user;

    private Date createdAt;
    
}
