package com.yang.lottery.interfaces.assember;

import com.yang.lottery.domain.activity.model.vo.ActivityVO;
import com.yang.lottery.rpc.activity.deploy.dto.ActivityDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.stream.Stream;

/**
 * @description: 活动对象转换配置
 * @author：杨超
 * @date: 2023/5/17
 * @Copyright：
 */
@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ActivityMapping extends IMapping<ActivityVO, ActivityDTO>{
    @Override
    List<ActivityDTO> sourceToTarget(List<ActivityVO> var1);
}
