package com.sswu.sanforyou;

/*
* 사용자 계정 정보 모델 클래스 */
public class UserAccount {
    private String DisplayName; //이름
    private String emailId; //이메일 아이디
    private String password; //비밀번호
    private String idToken; //Firebase Uid (고유 토큰정보)

    public UserAccount() { } //기본 생성자

    public String getName() { return DisplayName; } //이름

    public void setName(String name) { this.DisplayName = name; }

    public String getEmailId() { return emailId; } //이메일 아이디

    public void setEmailId(String emailId) { this.emailId = emailId; }

    public String getPassword() { return password; } //비밀번호

    public void setPassword(String password) { this.password = password; }

    public String getIdToken() { return idToken; } //아이디 토큰

    public void setIdToken(String idToken) { this.idToken = idToken; }
}
