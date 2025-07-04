package com.yupi.yupao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yupi.yupao.mapper.TagMapper;
import com.yupi.yupao.model.Tag;
import com.yupi.yupao.service.TagService;
import org.springframework.stereotype.Service;

/**
* @author Netis
* @description 针对表【tag(标签)】的数据库操作Service实现
* @createDate 2025-06-19 14:51:37
*/
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag>
    implements TagService{

}




