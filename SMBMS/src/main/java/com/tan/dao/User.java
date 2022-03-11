package com.tan.dao;

import java.util.Date;

/**
 * lomnok是一个在Java开发过程中用注解的方式，简化了 JavaBean 的编写，避免了冗余和样板式代码而出现的插件,让编写的类更加简洁。
 *
 * @NonNull 给方法参数增加这个注解会自动在方法内对该参数进行是否为空的校验，如果为空，则抛出NPE（NullPointerException）。
 * @Getter/@Setter** 使用在成员变量或者类上，为特定成员变量/类中所有变量生成Getter/Setter方法。
 * @ToString 用在类上，可以自动覆写toString方法，当然还可以加其他参数，例如@ToString(exclude=”id”)排除id属性，或者@ToString(callSuper=true, includeFieldNames=true)调用父类的toString方法，包含所有属性。
 * @EqualsAndHashCode 自动生成hashCode() 和 equals()方法。
 * @Data 注解在类上，相当于同时使用了@ToString、@EqualsAndHashCode、@Getter、@Setter和@RequiredArgsConstrutor
 */
public class User {
    private Integer id;             //主键
    private String UserCode;        //用户编码
    private String UserName;       //用户名
    private String UserPassword;    //用户密码
    private Integer gender;         //用户性别
    private Date birthday;          //用户生日
    private String phone;           //用户电话号码
    private String address;         //用户地址
    private Integer userRole;       //用户角色
    private Integer createionBy;    //创建者
    private Date createtionData;    //创建日期
    private Integer modifyBy;       //更改者
    private Date modifyData;        //更改日期
    //计算类数据
    private Integer age;
    private String userRoleName;

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserCode() {
        return UserCode;
    }

    public void setUserCode(String userCode) {
        UserCode = userCode;
    }

    public String getUserPassword() {
        return UserPassword;
    }

    public void setUserPassword(String userPassword) {
        UserPassword = userPassword;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getUserRole() {
        return userRole;
    }

    public void setUserRole(Integer userRole) {
        this.userRole = userRole;
    }

    public Integer getCreateionBy() {
        return createionBy;
    }

    public void setCreateionBy(Integer createionBy) {
        this.createionBy = createionBy;
    }

    public Date getCreatetionData() {
        return createtionData;
    }

    public void setCreatetionData(Date createtionData) {
        this.createtionData = createtionData;
    }

    public Integer getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(Integer modifyBy) {
        this.modifyBy = modifyBy;
    }

    public Date getModifyData() {
        return modifyData;
    }

    public void setModifyData(Date modifyData) {
        this.modifyData = modifyData;
    }

    public Integer getAge() {
        Date date = new Date();
        //As of JDK version 1.1, replaced by Calendar.get(Calendar.YEAR) - 1900.
        age = createtionData.getYear() - birthday.getYear();
        return age;
    }

    public String getUserRoleName() {
        return userRoleName;
    }

    public void setUserRoleName(String userRoleName) {
        this.userRoleName = userRoleName;
    }
}
