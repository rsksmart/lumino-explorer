import React, {Component} from "react";
import NodeItem from "./NodeItem";

export default class NodeList extends Component {
    render =()=> {
        let nodesItemList = this.props.nodeList.map((e)=>{
            return <NodeItem node_address={e.node_address} rns_address={e.rns_address} openChannelsCount={e.channels.length}/>
        });

        return <ul className="list-unstyled row channel-list">
            {nodesItemList}
        </ul>
    }
}
