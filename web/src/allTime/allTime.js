import React from "react";
import { connect } from "react-redux";
import { loadAllTime, toggleSearch } from "./allTime.actions";
import "./allTime.css";
import { Button, ButtonGroup } from 'reactstrap';

const TableRender = (props) => {
  if(!props.error) {
    return (
      <table className="lbTable table table-bordered table-hover">
        <thead>
          <tr>
            <th scope="col">#</th>
            <th scope="col">Name</th>
            <th scope="col">All Time</th>
            <th scope="col">Last 30 Days</th>
          </tr>
        </thead>
        <tbody>
          {props.docs.map((n, idx) => {
            return (
              <tr
                key={n.id}
              >
                <td>{idx + 1}</td>
                <td>{n.name}</td>
                <td>{n.totalPoints}</td>
                <td>{n.last30Days}</td>
              </tr>
            );
          })}
        </tbody>
      </table>
    );
  } else {
    return (<h5 className="error text-center">Error feching result</h5>);
  }
}

export class AllTimeComponent extends React.Component {
  constructor(props) {
    super(props);
    this.searchByAllTime = this.searchByAllTime.bind(this);
    this.searchByLast30Days = this.searchByLast30Days.bind(this);
  }

  componentDidMount() {
    this.props.loadAllTime();
  }

  componentDidUpdate(prevProps, prevState) {
    if (prevProps.toggleString !== this.props.toggleString) {
      this.props.loadAllTime();
    }
  }

  searchByAllTime() {
      this.props.toggleSearch("/api/leaderboard/all")
  }

  searchByLast30Days() {
    this.props.toggleSearch("/api/leaderboard/last30days")
  }

  render() {
    return (
      <div className="content">
        <h1>Leaderboard</h1>
        <h5 className="radioHeading">Show Campers for</h5>
        <ButtonGroup>
          <Button color="primary" onClick={() => this.searchByAllTime()} active={this.props.toggleString === '/api/leaderboard/all'}>All Time</Button>
          <Button color="primary" onClick={() => this.searchByLast30Days()} active={this.props.toggleString === '/api/leaderboard/last30days'}>Last 30 Days</Button>
        </ButtonGroup>
        <TableRender error={this.props.error} docs={this.props.docs} />
      </div>
    );
  }
}

export const mapStateToProps = state => ({
  docs: state.allTime.docs,
  error: state.allTime.error,
  toggleString: state.allTime.toggleString
});

const mapDispatchToProps = {
    loadAllTime,
    toggleSearch
};

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(AllTimeComponent);