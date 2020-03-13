package devy.moaview.service;

import devy.moaview.service.mapper.TargetContentsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 타겟 콘텐츠 관련 비즈니스 로직을 처리하는 서비스
 *
 * @author devy
 */
@Service
public class TargetContentsService {

    @Autowired
    private TargetContentsMapper targetContentsMapper;


}
