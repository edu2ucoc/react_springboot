import './App.css';
import Basic from './components/Basic';

/**
 * 메인 화면 프레임
 * @returns 
 */
function App() {
  return (
    <div>
      <fieldset>스프링부트-리액트 연동</fieldset>
      <fieldset>
        <Basic />
      </fieldset>      
    </div>
  );
}

export default App;
