import React from 'react';

const Saved = () => (
    <div
        style={{
            backgroundColor: '#4B7FFF',
            width: '100vw',
            height: '100vh',
            display: 'flex',
            flexDirection: 'column',
        }}
    >
        {/* status bar */}
        <div
            style={{
                height: '44px',
                padding: '0 16px',
                display: 'flex',
                justifyContent: 'space-between',
                alignItems: 'center',
                color: '#fff',
                fontSize: '17px',
            }}
        >
            <span>9:41</span>
            <div style={{ display: 'flex', alignItems: 'center' }}>
                {/* replace these with real SVGs or icons */}
                <span style={{ marginRight: '6px' }}>📶</span>
                <span style={{ marginRight: '6px' }}>📡</span>
                <span>🔋</span>
            </div>
        </div>

        {/* logo placeholder */}
        <div
            style={{
                flex: 1,
                display: 'flex',
                justifyContent: 'center',
                alignItems: 'center',
            }}
        >
            <img
                src="/placeholder-logo.png"
                alt="PaceBeats Logo"
                style={{ width: '120px', height: 'auto' }}
            />
        </div>
    </div>
);

export default Saved;
