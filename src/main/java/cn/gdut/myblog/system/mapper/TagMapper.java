package cn.gdut.myblog.system.mapper;

import cn.gdut.myblog.system.entity.SysTag;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

public interface TagMapper extends BaseMapper<SysTag> {

    List<SysTag> findByArticleId(Long id);
}
