package com.ttrip.api.service;

import com.ttrip.api.dto.DataResDto;
import com.ttrip.api.dto.fcmMessageDto.FcmMessageReqDto;
import com.ttrip.api.dto.fcmMessageDto.FromFlaskDto;
import com.ttrip.core.entity.member.Member;

import java.io.IOException;

public interface FcmService {
    DataResDto<?> sendMessageTo(Member member, FcmMessageReqDto fcmMessageReqDto)  throws IOException;
    DataResDto<?> face(FromFlaskDto fromFlaskDto)  throws IOException;
    void rate() throws IOException;
    void liveRate() throws IOException;
}
