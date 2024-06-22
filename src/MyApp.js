export function Header() {
   return (
      <div>
         <h1>Header</h1>
      </div>
   );

}
export function Employee(data) {
   return (
      <div><p>Name : {data.name}</p>
         <p>Salary : {data.salary}</p>
         <Department dept={data.dept} head={data.head} />
      </div>
   );
}
const Department = (deptInfo) => {
   return (
      <div>
         <p>Dept Name is : <b>{deptInfo.dept}</b></p>
         <p>Dept Head is : <b>{deptInfo.head}</b></p>
      </div>
   );
}
