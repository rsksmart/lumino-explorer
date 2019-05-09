import React, {Component} from "react";
import ChannelItem from "./ChanneItem";

export default class ChannelList extends Component {
    render =()=> {
        let channelsItemList = this.props.channelList.map((e)=>{
            return <ChannelItem
                key={e.channel_identifier}
                id={e.channel_identifier}
                from_address={e.from_address}
                to_address={e.to_address}
                to_rns_address={e.to_rns_address}
                from_rns_address={e.from_rns_address}
                token_name={e.token_name}
                state={e.state}
                token={e.token_address}/>
        });

        return <ul className="list-unstyled row channel-list">
            {channelsItemList}
        </ul>
    }
}
