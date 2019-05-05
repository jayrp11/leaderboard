import React from "react";
import { Route } from "react-router-dom";
import { connect } from "react-redux";
import { withRouter } from "react-router-dom";
import AllTimeComponent from "./allTime/allTime";

class App extends React.Component {
  render() {
    return (
      <div>
        <main>
          <Route exact path="/" component={AllTimeComponent} />
        </main>
      </div>
    );
  }
}

const mapStateToProps = state => ({});

const mapDispatchToProps = dispatch => ({});

export default withRouter(
  connect(
    mapStateToProps,
    mapDispatchToProps
  )(App)
);
