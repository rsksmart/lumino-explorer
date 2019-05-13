import React, {Component} from "react";
import {CHANNEL, NODE, TOKEN} from "../../lib/search/searchConstants";

export default class FeedItem extends Component {
    constructor(props) {
        super(props);
        this.state = {
            isActive: true,
        };

        this.hideAlert = this.hideAlert.bind(this);
    }

    hideAlert() {
        this.setState({
            isActive: false,
        });
    }

    render =()=> {
        const delayAnimation = {
            animationDelay: this.props.delay,
        };

        let icon = null;
        let feedContent = <div className="border-left ml-3 pl-3"></div>;
        let feedItem = this.props.feedItem;

        switch (feedItem.type.toLowerCase()) {
            case TOKEN:
                icon = 'fal fa-lg fa-coins';
                feedContent = <div className="border-sm-left ml-sm-3 pl-sm-3 col fs-14">

                    <p className="m-0">
                        <span><b>{feedItem.type}</b> registered</span>
                    </p>
                    <p className="m-0 w-75 text-truncate">
                        Name: <b className="text-green">{feedItem.data.token_name}</b>
                    </p>
                    <p className="m-0 w-75 text-truncate">
                        Symbol: <span className="text-green">{feedItem.data.token_symbol}</span>
                    </p>
                    <p className="m-0 w-75 text-truncate">
                        Decimals: <span className="text-green">{feedItem.data.token_decimals}</span>
                    </p>
                </div>;
                break;
            case CHANNEL:
                icon = 'fal fa-lg fa-chart-network d-none d-sm-block';
                feedContent = <div className="border-sm-left ml-sm-3 pl-sm-3 col fs-14">
                    <p className="m-0">
                        <span><b>{feedItem.type}</b> <b className="text-green">{feedItem.id}</b>  was <b>{feedItem.data.state}</b></span>
                    </p>
                    <p className="m-0 w-75 text-truncate">
                       From: <span className="text-green">{feedItem.data.from_address}</span>
                    </p>
                    <p className="m-0 w-75 text-truncate">
                        To: <span className="text-green">{feedItem.data.to_address}</span>
                    </p>
                    <p className="m-0 w-75 text-truncate">
                        Token: <span className="text-green">{feedItem.data.token_address}</span>
                    </p>
                </div>;
                break;
            case NODE:
                icon = 'fal fa-lg fa-user-friends d-none d-sm-block';
                feedContent = <div className="border-sm-left ml-sm-3 pl-sm-3 col fs-14">
                    <p className="m-0 w-75">
                        <span><b>Node</b> <b className="text-green">{feedItem.data.rns_address}</b>  was added to the network</span>
                    </p>
                    <p className="m-0 w-75 text-truncate">
                       RSK Address: <span className="text-green">{feedItem.data.address}</span>
                    </p>
                    <p className="m-0 w-75 text-truncate">
                        RNS Address: <span className="text-green">{feedItem.data.rns_address}</span>
                    </p>
                </div>;
                break;
            default:
                break;
        }

        return <li>
            {this.state.isActive && <div className="w-100 feed-item bg-white rounded shadow-sm p-3 px-sm-4 py-sm-3 d-sm-flex align-items-center fadeInDown animated mb-2" style={delayAnimation}>
                <i className={icon}> </i>
                {feedContent}
                <button type="button" className="close feed-item__close position-absolute" data-dismiss="alert" aria-label="Close" onClick={() => this.hideAlert()}>
                    <i className="fal fa-times"> </i>
                </button>
                {this.props.text}
            </div>}
        </li>
    }

}
