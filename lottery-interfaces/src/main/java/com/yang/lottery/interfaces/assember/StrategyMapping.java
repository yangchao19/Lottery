package com.yang.lottery.interfaces.assember;

import com.yang.lottery.domain.strategy.model.vo.StrategyDetailBriefVO;
import com.yang.lottery.rpc.activity.deploy.dto.StrategyDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.stream.Stream;

/**
 * @description:
 * @author：杨超
 * @date: 2023/9/17
 * @Copyright：
 */
@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StrategyMapping extends IMapping<StrategyDetailBriefVO,StrategyDTO>{
    @Override
    StrategyDTO sourceToTarget(StrategyDetailBriefVO var1);

    @Override
    StrategyDetailBriefVO targetToSource(StrategyDTO var1);

    @Override
    List<StrategyDTO> sourceToTarget(List<StrategyDetailBriefVO> var1);

    @Override
    List<StrategyDetailBriefVO> targetToSource(List<StrategyDTO> var1);

    @Override
    List<StrategyDTO> sourceToTarget(Stream<StrategyDetailBriefVO> stream);

    @Override
    List<StrategyDetailBriefVO> targetToSource(Stream<StrategyDTO> stream);
}
