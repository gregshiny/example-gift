-- gifts
create table gifts
(
    id                  bigint auto_increment primary key comment 'ID',
    gift_token          varchar(255) not null comment 'gift_token',
    buyer_user_id       varchar(255) not null comment '구매자 아이디',
    order_token         varchar(255) not null comment 'order_token',
    status              varchar(30)  not null default 'INIT' comment '상태',
    push_type           varchar(30)  not null default 'KAKAO' comment '푸시타입',
    gift_receiver_name  varchar(255) not null comment '선물 수령자명',
    gift_receiver_phone varchar(255) not null comment '선물 수령자 휴대폰번호',
    gift_message        varchar(255) not null comment '선물 메시지',
    receiver_name       varchar(30) null comment '수령자명',
    receiver_phone      varchar(30) null comment '수령자 휴대폰번호',
    receiver_zipcode    varchar(10) null comment '수령자 우편번호',
    receiver_address1   varchar(255) null comment '수령자 주소1',
    receiver_address2   varchar(255) null comment '수령자 주소2',
    etc_message         varchar(255) null comment '남기는 말',
    paid_at             datetime(6) null comment '구매 일시',
    pushed_at           datetime(6) null comment '푸시발송 일시',
    accepted_at         datetime(6) null comment '수락 일시',
    expired_at          datetime(6) null comment '만료 일시',
    created_at          datetime(6) not null comment '생성 일시',
    updated_at          datetime(6) null comment '수정 일시'
) comment 'gifts' charset = utf8mb4;

create
index gifts_idx01 on gifts (created_at);

create
index gifts_idx02 on gifts (updated_at);

create
index gifts_idx03 on gifts (gift_token);

create
index gifts_idx04 on gifts (order_token);
