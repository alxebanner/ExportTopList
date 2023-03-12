package com.uid939948.DO.Emoticons;


import lombok.Data;

import java.util.List;

@Data
public class TestEmoptions {
   private List<Emoticons> list;
   private String pkg_name;
    private int pkg_perm ;
    private List<Top_show_recent> list1;

 private List<Recently_used_emoticons> list2;
    private int unlock_identity;
    private int pkg_id;
    private List<Top_show> list3;
    private  String pkg_descript;
    private  String current_cover;
    private  int  pkg_type;
    private int unlock_need_gift;
}
