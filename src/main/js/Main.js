import React from 'react'
import { Switch, Route } from 'react-router-dom'
import Home from './Home'
import Transaction from './Transaction'
import Checkout from './Checkout'

const Main = () => (
  <main>
    <Switch>
      <Route exact path='/' component={Home}/>
      <Route path='/transactions' component={Transaction}/>
      <Route path='/checkout' component={Checkout}/>
    </Switch>
  </main>
)

export default Main
