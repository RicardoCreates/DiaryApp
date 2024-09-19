import {useState, useEffect} from 'react'
import './App.css'

function App() {
    const [entries, setEntries] = useState([]);

    useEffect(() => {
        const fetchEntries = async () => {
            try {
                const response = await fetch('http://localhost:8080/api/diary');
                if (response.ok) {
                    const data = await response.json();
                    setEntries(data);
                } else {
                    console.error('Error fetching entries:', response.statusText);
                }
            } catch (error) {
                console.error('Fetch error:', error);
            }
        };

        fetchEntries();
    }, []);

    return (
        <div className="App">
            <h1>Diary Entries</h1>
            <ul>
                {entries.map((entry) => (
                    <li key={entry.id}>
                        <strong>{entry.name}</strong>: {entry.entry}
                    </li>
                ))}
            </ul>
        </div>
    );
}

export default App;