import React, {Component} from "react";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";
import {pollFeed} from "../../actions/feedActions";
import PollingContainer from "../../genericContainers/PollingContainer";
import FeedItem from "../../components/feeds/feedItem";

class FeedContainer extends Component {
    render=()=> {
        return <PollingContainer
            render={this.renderPolling}
            pollAction={this.pollFeedContent}
            dueTim={0}
            periodOfScheduler={2000}
        />
    };

    pollFeedContent =()=> {
        this.props.pollFeed();
    };

    renderPolling=()=> {
        let feedItemsList = [];
        if (this.props.feedData) {
            feedItemsList = this.props.feedData;
        }

        let feedItemsListFinal = feedItemsList.map((e)=>{
          let delay = (e.id*100)/2 + 'ms';
          return <FeedItem feedItem={e} key={e.id} delay={delay} />
        });

        return <div className="row mt-4 justify-content-center">
            <ul className="list-unstyled col-lg-8 col-xl-6 py-3 px-4 feed-list-container mb-0">
               {feedItemsListFinal}
            </ul>
        </div>
    }
}

const mapStateToProps = (state) => {
    return {
        feedData: state.feedReducer.feedData,
    }
};

const mapDispatchToProps = (dispatch) => {
    const actions = {
        pollFeed: pollFeed,
    };
    return bindActionCreators(actions, dispatch)
};

export default connect(mapStateToProps, mapDispatchToProps)(FeedContainer);
