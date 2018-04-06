const React = require('react');
const client = require('./client');

class TransactionInfo extends React.Component{
	render() {
		return (
			<tr>
				<td>{this.props.transaction.id}</td>
				<td>{this.props.transaction.buyer.name}</td>
				<td>{this.props.transaction.payment.amount}</td>
				<td>{this.props.transaction.payment.type}</td>
				<td>{this.props.transaction.payment.status}</td>
			</tr>
		)
	}
}

class TransactionList extends React.Component{
	render() {
		var transactions = this.props.transactions.map(transaction =>
			<TransactionInfo key={transaction.id} transaction={transaction}/>
		);
		return (
			<table>
				<tbody>
					<tr>
						<th>Id</th>
						<th>Buyer</th>
						<th>Amount</th>
						<th>Payment Type</th>
						<th>Status</th>
					</tr>
					{transactions}
				</tbody>
			</table>
		)
	}
}

export default class Transaction extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
    		clientId: '',
    		transactions: []
    };

    this.handleChange = this.handleChange.bind(this);
    this.handleOnClick = this.handleOnClick.bind(this);
  }

  handleChange(event) {
	  this.setState({clientId: event.target.value});
  }

  handleOnClick() {
	  if (this.state.clientId) {
		  client({method: 'GET', path: '/api/clients/{clientId}/transactions', params: { clientId : this.state.clientId}}).done(response => {
			  this.setState({transactions: response.entity});
		  });
	  } else {
		  alert('Enter a valid clientId')
	  }
  }

  render() {
	const hidden = (this.state.transactions.length == 0);
	return (
		<div className="container">
	  		<div className="row">
		      	<div className="col-25">
			        <label>Client Id:</label>
			    </div>
			    <div className="col-75">
		        	<input name="clientId" type="text" value={this.state.clientId} onChange={this.handleChange} required/>
		        </div>
		    </div>
		    <div className="row">
	        	<input type="button" onClick={this.handleOnClick} value="Search" />
    		</div>
	        { hidden ? <p>No data</p> : <TransactionList transactions={this.state.transactions}/> }
		</div>
	)
  }
}