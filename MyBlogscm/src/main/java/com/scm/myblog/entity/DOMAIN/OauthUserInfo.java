package com.scm.myblog.entity.DOMAIN;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 用户第三方登陆信息表
 * @TableName oauth_user_info
 */
@TableName(value ="oauth_user_info")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OauthUserInfo implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * OAuth用户唯一的id
     */
    private String uid;

    /**
     * OAuth用户名
     */
    private String username;

    /**
     * OAuth平台的头像url
     */
    private String avatar;

    /**
     * 给的token
     */
    private String oauth_token;

    /**
     * oauth的过期时间
     */
    private Integer oauth_expireIn;

    /**
     * 平台id
     */
    private Integer oauth_platform_id;

    /**
     * 
     */
    private Integer is_delete;

    /**
     * 状态
     */
    private Integer status;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        OauthUserInfo other = (OauthUserInfo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUid() == null ? other.getUid() == null : this.getUid().equals(other.getUid()))
            && (this.getUsername() == null ? other.getUsername() == null : this.getUsername().equals(other.getUsername()))
            && (this.getAvatar() == null ? other.getAvatar() == null : this.getAvatar().equals(other.getAvatar()))
            && (this.getOauth_token() == null ? other.getOauth_token() == null : this.getOauth_token().equals(other.getOauth_token()))
            && (this.getOauth_expireIn() == null ? other.getOauth_expireIn() == null : this.getOauth_expireIn().equals(other.getOauth_expireIn()))
            && (this.getOauth_platform_id() == null ? other.getOauth_platform_id() == null : this.getOauth_platform_id().equals(other.getOauth_platform_id()))
            && (this.getIs_delete() == null ? other.getIs_delete() == null : this.getIs_delete().equals(other.getIs_delete()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUid() == null) ? 0 : getUid().hashCode());
        result = prime * result + ((getUsername() == null) ? 0 : getUsername().hashCode());
        result = prime * result + ((getAvatar() == null) ? 0 : getAvatar().hashCode());
        result = prime * result + ((getOauth_token() == null) ? 0 : getOauth_token().hashCode());
        result = prime * result + ((getOauth_expireIn() == null) ? 0 : getOauth_expireIn().hashCode());
        result = prime * result + ((getOauth_platform_id() == null) ? 0 : getOauth_platform_id().hashCode());
        result = prime * result + ((getIs_delete() == null) ? 0 : getIs_delete().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", uid=").append(uid);
        sb.append(", username=").append(username);
        sb.append(", avatar=").append(avatar);
        sb.append(", oauth_token=").append(oauth_token);
        sb.append(", oauth_expireIn=").append(oauth_expireIn);
        sb.append(", oauth_platform_id=").append(oauth_platform_id);
        sb.append(", is_delete=").append(is_delete);
        sb.append(", status=").append(status);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}