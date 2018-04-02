'use strict';

const React = require('react');
const ReactDOM = require('react-dom');
const client = require('./client');

class PaymentTypeForm extends React.Component {
  constructor(props) {
	    super(props);
  }

  render() {
	  return (
		<div>
			<div className="row">
				<div className="col-25">
					<label>Total Amount:</label>
				</div>
				<div className="col-75">
			      <input name="amount" type="text" value={this.props.amount} onChange={this.props.onChange} required/>
			    </div>
		    </div>
			<div className="row">
				<div className="col-25">
					<label>Choose payment type:</label>
				</div>
				<div className="col-75">
			      <select name="paymentType" value={this.props.paymentType} onChange={this.props.onChange} required>
			        <option value="creditcard">Credit Card</option>
			        <option value="boleto">Boleto</option>
			      </select>
			    </div>
		    </div>
	    </div>
	)
  }
}

class CardForm extends React.Component {
	constructor(props) {
		super(props);
		this.onInputChange = this.onInputChange.bind(this);
		this.onInputDateChange = this.onInputDateChange.bind(this);
	}

	onInputChange(event) {
		var value = event.target.value.replace(/\D/g, "");
		event.target.value = value;
	}

	onInputDateChange(event) {
		this.onInputChange(event);
		var dateString = event.target.value.replace(/\//g, "");
		if (dateString.length > 2) {
			var month = dateString.substring(0, 2);
			var year = dateString.substring(2);
			if (parseInt(month) > 12) {
				event.target.value = '';
			} else if (dateString.length == 6) {
				var currentMonth = new Date().getMonth();
				var currentYear = new Date().getFullYear();
				if (year < currentYear || (year == currentYear && currentMonth > month - 1)) {
					event.target.value = '';
				} else {
					event.target.value = month + '/' + year;
				}
			} else {
				event.target.value = month + '/' + year;
			}
		}
	}

	render() {
		const showIssuer = this.props.issuer != '';
		return (
			<div>
				<div className="row">
					<div className="col-25">
						<label>Card Holder Name:</label>
					</div>
					<div className="col-75">
						<input name="cardHolderName" type="text" value={this.props.cardHolderName} onChange={this.props.onChange} required/>
					</div>
				</div>
				<div className="row">
					<div className="col-25">
						<label>Card Number:</label>
					</div>
					<div className="col-75">
						<input name="number" type="text" value={this.props.number} onChange={this.props.onChange} maxLength="16" onInput={event => this.onInputChange(event)} required/>
						{ showIssuer ? <label>  {this.props.issuer}</label> : null }
					</div>
				</div>
				<div className="row">
					<div className="col-25">
						<label>Expiration Date:</label>
					</div>
					<div className="col-75">
						<input name="cardExpirationDate" type="text" value={this.props.cardExpirationDate} placeholder="MM/yyyy"
							onChange={this.props.onChange} onInput={event => this.onInputDateChange(event)} maxLength="7" required/>
					</div>
				</div>
				<div className="row">
					<div className="col-25">
						<label>CVV:</label>
					</div>
					<div className="col-75">
						<input name="cvv" type="text" value={this.props.cvv} maxLength="3" onChange={this.props.onChange} onInput={event => this.onInputChange(event)} required/>
					</div>
				</div>
			</div>
		)
	}
}

class CheckoutForm extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
    	clientId: '',
		name: '',
		email: '',
		cpf: '',
		amount: '',
		paymentType: 'creditcard',
		cardHolderName: '',
		number: '',
		cardExpirationDate: '',
		cvv: '',
		issuer: ''
    };

    this.handleCardChange = this.handleCardChange.bind(this);
    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  handleChange(event) {
    const name = event.target.name;
    this.setState({[name]: event.target.value});
  }

  handleNumberChange(event) {
	  this.setState({number: event.target.value });
	  
	  var issuer = Moip.Validator.cardType(event.target.value);
	  if (issuer) {
		  this.setState({issuer: issuer.brand});
	  } else {
	      this.setState({issuer: ''});
	  }
  }

  handleCardChange(event) {
	  const name = event.target.name;
	  if (name == 'number') {
		  this.handleNumberChange(event);
	  } else {
		  this.handleChange(event);
	  }
  }

  handleSubmit(event) {
    event.preventDefault();
    var client = {id: this.state.clientId};
    var buyer = {name: this.state.name, email: this.state.email, cpf: this.state.cpf};
    var card = {cardHolderName: this.state.cardHolderName, number: this.state.number, cardExpirationDate: this.state.cardExpirationDate, cvv: this.state.cvv};
    var payment = {amount: this.state.amount, type: this.state.paymentType, card: card};
    var data = {client: client, buyer: buyer, payment: payment};
    alert(JSON.stringify(data));
  }

  render() {
	const hidden = this.state.paymentType == 'creditcard';
    return (
      <form onSubmit={this.handleSubmit}>
      	<div className="container">
      		<h1>Moip Payments</h1>
      		<div className="row">
		      	<div className="col-25">
			        <label>Customer name:</label>
			    </div>
			    <div className="col-75">
		        	<input name="name" type="text" value={this.state.name} onChange={this.handleChange} required/>
		        </div>
        	</div>
        	<div className="row">
		        <div className="col-25">
			        <label>Email:</label>
			    </div>
			    <div className="col-75">
		        	<input name="email" type="text" value={this.state.email} onChange={this.handleChange}required />
		        </div>
        	</div>
        	<div className="row">
		        <div className="col-25">
			        <label>CPF:</label>
			    </div>
			    <div className="col-75">
		        	<input name="cpf" type="text" value={this.state.cpf} onChange={this.handleChange} required/>
		        </div>
        	</div>
	        <PaymentTypeForm onChange={ this.handleChange } paymentType={ this.state.paymentType } amount={ this.state.amount }/>
	        { hidden ? <CardForm onChange={ this.handleCardChange } cardHolderName={ this.state.cardHolderName } number={ this.state.number } 
	        			cardExpirationDate={ this.state.cardExpirationDate } cvv={ this.state.cvv } issuer={ this.state.issuer }/> : null }
	        <div className="row">
				<input type="submit" value="Finish" />
			</div>
		 </div>
      </form>
    );
  }
}


ReactDOM.render(
	<CheckoutForm />,
	document.getElementById('react')
)
