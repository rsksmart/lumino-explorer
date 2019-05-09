package org.rif.lumino.explorer.utils;

import org.rif.lumino.explorer.constants.LuminoExplorerConstants;
import org.rif.lumino.explorer.models.EventData;
import org.rif.lumino.explorer.models.enums.ChannelState;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

public class BlockProcessingUtils {

  // TODO remove this, use just last block
  public static BigInteger getLastProcessedEventBlock(Map<ChannelState, List<EventData>> events) {
    BigInteger open =
        events.get(ChannelState.Opened).size() > 0
            ? events
                .get(ChannelState.Opened)
                .get(events.get(ChannelState.Opened).size() - 1)
                .getBlockNumber()
            : LuminoExplorerConstants.BIG_INTEGER_ZERO;
    BigInteger closed =
        events.get(ChannelState.Closed).size() > 0
            ? events
                .get(ChannelState.Closed)
                .get(events.get(ChannelState.Closed).size() - 1)
                .getBlockNumber()
            : LuminoExplorerConstants.BIG_INTEGER_ZERO;
    BigInteger settled =
        events.get(ChannelState.Settled).size() > 0
            ? events
                .get(ChannelState.Settled)
                .get(events.get(ChannelState.Settled).size() - 1)
                .getBlockNumber()
            : LuminoExplorerConstants.BIG_INTEGER_ZERO;

    return max(open, closed, settled).add(LuminoExplorerConstants.BIG_INTEGER_ONE);
  }

  // TODO remove this, use just last block
  public static BigInteger getLastProcesseTokenNetworkEvent(List<EventData> events) {
    BigInteger latest = LuminoExplorerConstants.BIG_INTEGER_ZERO;
    if (!events.isEmpty()) {
      latest = events.get(events.size() - 1).getBlockNumber();
    }
    return latest.add(LuminoExplorerConstants.BIG_INTEGER_ONE);
  }

  // TODO remove this, use just last block
  public static BigInteger max(BigInteger first, BigInteger... rest) {
    BigInteger ret = first;
    for (BigInteger val : rest) {
      if (val.compareTo(ret) > 0) {
        ret = val;
      }
    }
    return ret;
  }

  // TODO remove this, use just last block
  public static boolean eventActivity(BigInteger lastBlock, BigInteger currentBlock) {
    return currentBlock.compareTo(lastBlock) > 0;
  }
}
