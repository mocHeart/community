package com.moc.community.service;

import com.moc.community.dao.*;
import com.moc.community.dto.NotificationDto;
import com.moc.community.dto.PaginationDto;
import com.moc.community.dto.QuestionDto;
import com.moc.community.enums.NotificationStatusEnum;
import com.moc.community.enums.NotificationTypeEnum;
import com.moc.community.exception.CustomizeErrorCodeEnum;
import com.moc.community.exception.CustomizeException;
import com.moc.community.mapper.NotificationMapper;
import com.moc.community.mapper.UserMapper;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class NotificationService {

    @Autowired
    private NotificationMapper notificationMapper;

    @Autowired
    private UserMapper userMapper;

    public PaginationDto list(Long userId, Integer page, Integer size) {
        PaginationDto<NotificationDto> paginationDto = new PaginationDto<>();
        NotificationExample example = new NotificationExample();

        example.createCriteria().andReceiverEqualTo(userId);
        example.setOrderByClause("gmt_create desc");

        Integer totalCount = (int) notificationMapper.countByExample(example);
        paginationDto.setPagination(totalCount, page, size);

        if (page < 1) page = 1;
        if (page > paginationDto.getTotalPage()) page = paginationDto.getTotalPage();

        int offset = size * (page - 1);
        List<Notification> notifications = notificationMapper.selectByExampleWithRowbounds(example, new RowBounds(offset, size));

        if (notifications.size() == 0) {
            return paginationDto;
        }

        List<NotificationDto> notificationDtos = new ArrayList<>();
        for (Notification notification : notifications) {
            NotificationDto notificationDto = new NotificationDto();
            BeanUtils.copyProperties(notification, notificationDto);
            notificationDto.setTypeName(NotificationTypeEnum.nameOfType(notification.getType()));
            notificationDtos.add(notificationDto);
        }
        paginationDto.setData(notificationDtos);
        return paginationDto;
    }

    public Long unreadCount(Long userId) {
        NotificationExample notificationExample = new NotificationExample();
        notificationExample.createCriteria()
                .andReceiverEqualTo(userId)
                .andStatusEqualTo(NotificationStatusEnum.UNREAD.getStatus());
        return notificationMapper.countByExample(notificationExample);
    }

    public NotificationDto read(Long id, User user) {
        Notification notification = notificationMapper.selectByPrimaryKey(id);
        if (notification == null) {
            throw new CustomizeException(CustomizeErrorCodeEnum.NOTIFICATION_NOT_FOUND);
        }
        if (!Objects.equals(notification.getReceiver(), user.getId())) {
            throw new CustomizeException(CustomizeErrorCodeEnum.READ_NOTIFICATION_FAIL);
        }

        notification.setStatus(NotificationStatusEnum.READ.getStatus());
        notificationMapper.updateByPrimaryKey(notification);

        NotificationDto notificationDTO = new NotificationDto();
        BeanUtils.copyProperties(notification, notificationDTO);
        notificationDTO.setTypeName(NotificationTypeEnum.nameOfType(notification.getType()));
        return notificationDTO;
    }
}
