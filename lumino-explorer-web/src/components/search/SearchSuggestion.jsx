import React, {Component} from "react";
import Autosuggest from 'react-autosuggest';
import {ADDRESS, CHANNEL, TOKEN} from "../../lib/search/searchConstants";
import Link from "react-router-dom/es/Link";

export default class SearchSuggestion extends Component {
    getSuggestionValue = suggestion => suggestion.name;

    renderSuggestion = suggestion => {
        let searchType = "fal mr-2 fa-lg align-middle fa-fw";
        let routeLink= '';

        switch (suggestion.type) {
            case TOKEN:
                searchType += ' fa-coins';
                routeLink = '/tokenDetail';
                break;
            case CHANNEL:
                searchType += ' fa-chart-network';
                routeLink = '/channelDetail';
                break;
            case ADDRESS:
                searchType += ' fa-address-card';
                routeLink = '/nodeDetail';
                break;
            default:
                searchType += '';
                break;
        }

        let suggestionItem;

        if (this.props.redirect) {
          suggestionItem = <Link className="btn btn-block text-left" to={routeLink} onClick={()=> this.props.selectedSuggestion(suggestion)}>
              <i className={searchType}>
              </i>
              {suggestion.value}
          </Link>;

        } else {
            suggestionItem = <div className="btn btn-block text-left">
                <i className={searchType}>
                </i>
                {suggestion.value}
            </div>;
        }

        return <div>
            {suggestionItem}
        </div>
    };

    onSuggestionsFetchRequested = ({ value } ) => {
        if(this.props.loadSuggestionsBegin){
            // Only if present, maybe you dont want to inform that the loading started.
            this.props.loadSuggestionsBegin()
        }
        if(value){
            this.props.onSuggestionsFetchRequested(value.trim().toLowerCase())
        }
    };

    render() {
        const { value, suggestions,onChange, onSuggestionsClearRequested } = this.props;
        const inputProps = {
            placeholder: 'Search...',
            value,
            onChange
        };
        return (
                <Autosuggest
                    suggestions={suggestions}
                    onSuggestionsFetchRequested={this.onSuggestionsFetchRequested}
                    onSuggestionsClearRequested={onSuggestionsClearRequested}
                    getSuggestionValue={this.getSuggestionValue}
                    renderSuggestion={this.renderSuggestion}
                    inputProps={inputProps}
                    onSuggestionSelected={this.props.onSuggestionSelected}
                />
        );
    }
}
