import React, {Component} from "react";
import NodeItem from "./NodeItem";

export default class NodeList extends Component {
    render =()=> {
        let nodesItemList = this.props.nodeList.map((e)=>{
            let channelLenght = 0;
            if (e.channels){
                channelLenght = e.channels.length;
            }
            return <NodeItem node_address={e.node_address} rns_address={e.rns_address} openChannelsCount={channelLenght}/>
        });

        return <ul className="list-unstyled row channel-list">
            {nodesItemList}
        </ul>
    }
}
