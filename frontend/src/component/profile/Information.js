import React, { useContext, useEffect, useState } from "react";
import RootContext from "../../context/RootContext";

function Information() {
  const context = useContext(RootContext);
  const [fname, setFname] = useState('');
  const [lname, setLname] = useState('');
  const [gender, setGender] = useState('');
  const [email, setEmail] = useState('');
  const [phone, setPhone] = useState('');

  console.log("Information.jsx: user:", context.user);
  useEffect(() => {
    const user = context.user;

    if (user !== null) {
      const customer = user.customer;
      setFname(customer.firstName);
      setLname(customer.lastName);
      setEmail(customer.email);
      setPhone(customer.phone);
    }
  });

  return (
    <div>
      <div className="">
        <form className="">
          <span className="text-xl font-medium">Personal Information</span>
          <span className="mt-2 flex space-x-4">
            <input type="text" name="" id="" value={fname} onChange={(e) => setFname(e.target.value)} placeholder="First Name" />
            <input type="text" name="" id="" value={lname} onChange={(e) => setLname(e.target.value)} placeholder="Last Name" />
          </span>
          <span className="mt-4 flex flex-col">
            <span>Your Gender</span>
            <span className="space-x-8">
              <span className="space-x-2">
                <input type="radio" name="gender" id="male" value={"male"} />
                <label className="cursor-pointer" htmlFor="male">Male</label>
              </span>
              <span className="space-x-2">
                <input type="radio" name="gender" id="female" value={"female"} />
                <label className="cursor-pointer" htmlFor="female">Female</label>
              </span>
            </span>
          </span>
          <span className="mt-4 flex flex-col">
            <span className="text-xl font-medium">Email Address</span>
            <input className="mt-2" type="text" name="" id="" value={email} onChange={(e) => setFname(e.target.value)} placeholder="Email Address" />
          </span>
          <span className="mt-4 flex flex-col">
            <span className="text-xl font-medium">Mobile Number</span>
            <input className="mt-2" type="text" name="" id="" value={phone} onChange={(e) => setFname(e.target.value)} placeholder="Phone Number" />
          </span>
          <button type="submit" className="mt-4 px-6 py-1.5 rounded bg-amber-600">Save</button>
        </form>
      </div>
    </div>
  );
}

export default Information;
