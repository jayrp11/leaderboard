import {
    ALLTIME_REQUEST,
    ALLTIME_SUCCESS,
    ALLTIME_FAILURE,
    TOGGLE_SEARCH
  } from "./allTime.actions";
  
  const allTimeReducer = (
    state = {
      docs: [],
      toggleString: "/api/leaderboard/all"
    },
    action
  ) => {
    switch (action.type) {
      case ALLTIME_SUCCESS:
        return {
          ...state,
          docs: action.response,
          error: ''
        };
      case ALLTIME_FAILURE:
        return {
          ...state,
          docs: [],
          error: action.errors.message
        }
      case TOGGLE_SEARCH:
        return {
          ...state,
          toggleString: action.toggleString
        }
      default:
        return state;
    }
  };
  
  export default allTimeReducer;
  