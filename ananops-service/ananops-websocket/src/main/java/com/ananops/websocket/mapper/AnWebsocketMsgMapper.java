package com.ananops.websocket.mapper;

import com.ananops.common.core.dao.BaseMapper;
import com.ananops.websocket.domain.AnWebsocketMsg;
import java.util.List;

/**
 * websocketMapper接口
 * 
 * @author ananops
 * @date 2020-06-08
 */
public interface AnWebsocketMsgMapper extends BaseMapper<AnWebsocketMsg>
{
    /**
     * 查询websocket
     * 
     * @param id websocketID
     * @return websocket
     */
    public AnWebsocketMsg selectAnWebsocketMsgById(Long id);

    /**
     * 查询websocket列表
     * 
     * @param anWebsocketMsg websocket
     * @return websocket集合
     */
    public List<AnWebsocketMsg> selectAnWebsocketMsgList(AnWebsocketMsg anWebsocketMsg);

    /**
     * 新增websocket
     * 
     * @param anWebsocketMsg websocket
     * @return 结果
     */
    public int insertAnWebsocketMsg(AnWebsocketMsg anWebsocketMsg);

    /**
     * 修改websocket
     * 
     * @param anWebsocketMsg websocket
     * @return 结果
     */
    public int updateAnWebsocketMsg(AnWebsocketMsg anWebsocketMsg);

    /**
     * 删除websocket
     * 
     * @param id websocketID
     * @return 结果
     */
    public int deleteAnWebsocketMsgById(Long id);

    /**
     * 批量删除websocket
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAnWebsocketMsgByIds(String[] ids);
}
