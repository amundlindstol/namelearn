package com.abli.namelearn.exception;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter
public class ErrorInfo {
    private String message;
    private String type;
}
