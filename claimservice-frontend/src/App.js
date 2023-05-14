import './App.css';
import "../node_modules/bootstrap/dist/css/bootstrap.min.css";
import Navbar from './layout/Navbar';
import Home from './pages/Home';
import { Route, BrowserRouter as Router, Routes } from 'react-router-dom';
import Addclaim from './users/Addclaim';
import EditClaim from "./users/EditClaim";

function App() {
  return (
    <div className="App">
      <Router>
        <Navbar />
        <Routes>
          <Route exact path="/" element={<Home />} />
          <Route exact path="/addclaim" element={<Addclaim />}/>
          <Route exact path="/editclaim/:id" element={<EditClaim />} />
        </Routes>
      </Router>
    </div>
  );
}

export default App;

