import React, {Component} from "react";
import { ReactComponent as Logo } from './logo-rif.svg';
import SearchSuggestion from "../../components/search/SearchSuggestion";

import {onChange, selectedSuggestion} from "../../actions/searchActions";
import {onSuggestionsFetchRequested} from "../../actions/searchActions";
import {onSuggestionsClearRequested} from "../../actions/searchActions";
import {loadSuggestionsBegin} from "../../actions/searchActions";

import {bindActionCreators} from "redux";
import {connect} from "react-redux";


class HeaderContainer extends Component {


    render =()=> {
        return <header className="w-100">
            <nav className="navbar navbar-light navbar-expand pt-4 py-lg-4 flex-column flex-md-row">
                <a className="navbar-brand" href="/">
                    <Logo />
                </a>
                <div className="mx-lg-auto bg-white shadow-sm search-suggestion-container d-flex align-items-center px-3 mt-2 mt-md-0 ml-md-3">
                    <SearchSuggestion
                        selectedSuggestion ={this.props.selectedSuggestion}
                        redirect={true}
                        suggestions={this.props.suggestions}
                        value={this.props.value}
                        isLoading={this.props.isLoading}
                        noSuggestions={this.props.noSuggestions}
                        onSuggestionsClearRequested={this.props.onSuggestionsClearRequested}
                        onSuggestionsFetchRequested={this.props.onSuggestionsFetchRequested}
                        onChange={this.props.onChange}
                        loadSuggestionsBegin={this.props.loadSuggestionsBegin}
                    />
                    <i className="fal fa-search fa-lg"></i>
                </div>
                <ul className="nav mt-3 mt-md-0 ml-md-3">
                    <li className="nav-item mr-2">
                        <a className="nav-link text-center rounded d-flex align-items-center" href="nodes">
                            <i className="fal fa-lg fa-user-friends"></i>
                            <span className="border-left ml-2 pl-2 align">View all <br/> Nodes</span>
                        </a>
                    </li>
                    <li className="nav-item">
                        <a className="nav-link text-center rounded d-flex align-items-center" href="channels">
                            <i className="fal fa-lg fa-chart-network"></i>
                            <span className="border-left ml-2 pl-2 align">View all <br/> Channels</span>
                        </a>
                    </li>
                </ul>
            </nav>

        </header>
    };

}

const mapStateToProps = (state) => {
    return {
        searchResults: state.searchReducer.results,
        value: state.searchReducer.value,
        suggestions: state.searchReducer.suggestions,
        isLoading: state.searchReducer.isLoading,
        noSuggestions: state.searchReducer.noSuggestions,
        tokens: state.dashboardReducer.tokens,
    }
};

const mapDispatchToProps = (dispatch) => {
    const actions = {
        onChange:onChange,
        onSuggestionsFetchRequested:onSuggestionsFetchRequested,
        onSuggestionsClearRequested:onSuggestionsClearRequested,
        loadSuggestionsBegin:loadSuggestionsBegin,
        selectedSuggestion: selectedSuggestion,
    };

    return bindActionCreators(actions, dispatch)
};

export default connect(mapStateToProps, mapDispatchToProps)(HeaderContainer);
