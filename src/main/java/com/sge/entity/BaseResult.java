package com.sge.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by wzx on 2021/12/7.
 */
@Getter
@Setter
public class BaseResult<T> {
    private boolean success;
    private T result;
    private String message;
}
