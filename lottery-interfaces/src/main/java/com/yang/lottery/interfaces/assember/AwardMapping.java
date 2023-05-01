package com.yang.lottery.interfaces.assember;

import com.yang.lottery.domain.strategy.model.vo.DrawAwardVO;
import com.yang.lottery.rpc.activity.booth.dto.AwardDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;


/**
 * @description: 对象转换配置
 * @author：杨超
 * @date: 2023/4/13
 * @Copyright：
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface AwardMapping extends IMapping<DrawAwardVO, AwardDTO>{

    @Mapping(target = "userId" ,source = "uId")
    @Override
    AwardDTO sourceToTarget(DrawAwardVO var1);

    @Override
    DrawAwardVO targetToSource(AwardDTO var1);
}
