package devy.moaview.service;

import devy.moaview.service.mapper.ContentsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 콘텐츠 관련 비즈니스 로직을 처리하는 서비스
 *
 * @author devy
 */
@Service
public class ContentsService {

    @Autowired
    private ContentsMapper contentsMapper;

}
