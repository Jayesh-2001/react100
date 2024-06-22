import './App.css';

function FormData() {
   return (
      <pre>
         <form style={{ display: 'flex', flexDirection: 'column', alignItems: 'center' }}>
            <div>
               <label style={{ margin: '5px' }}>User_Name: </label>
               <input name="Name" style={{ width: '20vw', height: '3vh', borderRadius: '15px', padding: '15px', marginBottom: '10px', fontSize: '20px' }} />
            </div>
            <div>
               <label style={{ margin: '5px' }}>Password: </label>
               <input name="password" style={{ width: '20vw', height: '3vh', borderRadius: '15px', padding: '15px', marginBottom: '10px', fontSize: '20px' }} />
            </div>
            <input type="Submit" style={{ width: '15vw', height: '5vh', borderRadius: '15px', margin: '15px', fontSize: '20px' }} />
         </form>
      </pre>
   );
}

export default FormData;