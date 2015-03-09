package com.zblog.web.front.validator;

import java.util.regex.Pattern;

import com.zblog.core.dal.entity.Comment;
import com.zblog.core.plugin.MapContainer;
import com.zblog.core.util.CommRegular;
import com.zblog.core.util.StringUtils;

public class CommentValidator{

  public static MapContainer validate(Comment comment){
    MapContainer result = new MapContainer();
    if(StringUtils.isBlank(comment.getPostid())){
      result.put("msg", "文章id不合法");
    }else if(StringUtils.isBlank(comment.getEmail()) || !comment.getEmail().matches(CommRegular.EMAIL)){
      result.put("msg", "邮箱格式不合法");
    }else if(StringUtils.isBlank(comment.getUrl()) || !comment.getUrl().matches(CommRegular.DOMAIN)){
      result.put("msg", "站点格式不合法 ");
    }else if(StringUtils.isBlank(comment.getCreator())){
      result.put("msg", "需填写用户昵称");
    }else if(comment.isApproved()){
      result.put("msg", "非法请求");
    }else if(StringUtils.isBlank(comment.getContent()) || !Pattern.matches("[\u4e00-\u9fa5]", comment.getContent())){
      result.put("msg", "请填写评价内容");
    }

    return result;
  }

}