package com.project.SoyouCrypto.login.dto;

import com.project.SoyouCrypto.login.model.OutputIndex;
import com.project.SoyouCrypto.login.model.MomentumDataAll;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class MomentumGraphResponseDto {
    private List<MomentumDataAll> momentumDataAll;
    private List<OutputIndex> indexAll;


    public MomentumGraphResponseDto(List<MomentumDataAll> momentumDataAll, List<OutputIndex> indexAll) {
        this.momentumDataAll = momentumDataAll;
        this.indexAll = indexAll;

    }
}