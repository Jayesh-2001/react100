import logo from './logo.svg';
import './App.css';
import FormData from './FormData';
import { Header,Employee } from './MyApp';

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <FormData />
        <div>
          <Header />
          <Employee name="Jayesh" salary="75000" dept="Dev" head="I'm the Head" />
        </div>

      </header>
    </div>
  );
}

export default App;
