package com.example.web.bean;

import com.example.validation.constraint.Equals;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Named
@ViewScoped
@Equals(property1 = "email1", property2 = "email2",
        message = "メールアドレスとメールアドレス（確認）は一致させてください")
public class CompareBean implements Serializable {

    @NotBlank(message = "メールアドレスは必須です")
    @Size(min = 3, message = "メールアドレスは{min}文字以上です")
    private String email1;

    @NotBlank(message = "メールアドレス（確認）は必須です")
    @Size(min = 3, message = "メールアドレス（確認）は{min}文字以上です")
    private String email2;

    private String message;

    public String submit() {
        message = "正しく入力されました！";
        return "compare.xhtml";
    }

    public void setEmail1(String email1) {
        this.email1 = email1;
    }

    public void setEmail2(String email2) {
        this.email2 = email2;
    }

    public String getMessage() {
        return message;
    }

    public String getEmail1() {
        return email1;
    }

    public String getEmail2() {
        return email2;
    }
}
