package org.rif.lumino.explorer.scheduled;

import org.rif.lumino.explorer.managers.LuminoNodeManager;
import org.rif.lumino.explorer.models.documents.LuminoNode;
import org.rif.lumino.explorer.models.dto.ChannelDTO;
import org.rif.lumino.explorer.models.enums.ChannelState;
import org.rif.lumino.explorer.services.ChannelService;
import org.rif.lumino.explorer.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class AliveNodeCheckTask {

    @Autowired
    private LuminoNodeManager luminoNodeManager;

    @Autowired
    private ChannelService chanelService;

    @Value("${lumino.explorer.api.scheduled.task.alivenodecheck.delete.time.condition.in.minutes}")
    private Integer deleteTimeConditionInMinutes;

    private static final Logger logger = LoggerFactory.getLogger(AliveNodeCheckTask.class);

    @Scheduled(cron = "${lumino.explorer.api.scheduled.task.alivenodecheck.cron.check.node.alive}")
    public void checkNodesAlive() {

        List<LuminoNode> luminoNodeList = luminoNodeManager.getAll();
        for (LuminoNode luminoNode : luminoNodeList) {

            List<ChannelDTO> channelDTOS = chanelService.getChannelsByLuminoNodeAndState(luminoNode.getNodeAddress(), ChannelState.Opened.toString());

            if (luminoNode.getLastAliveSignal() != null && channelDTOS.isEmpty()) {
                long minutes = DateUtil.getMinutesDifferenceUTC(luminoNode.getLastAliveSignal(), LocalDateTime.now(Clock.systemUTC()));
                if (minutes > deleteTimeConditionInMinutes) {
                    luminoNodeManager.deleteNodeById(luminoNode.getNodeAddress());
                    logger.info("The node with address: " +
                            luminoNode.getNodeAddress() +
                            "has been deleted because not exist alive signal on the last " +
                            deleteTimeConditionInMinutes + " minutes");
                }
            }
        }
    }


}
