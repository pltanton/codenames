import * as React from 'react'
import SockJsClient from 'react-stomp';


export class Home extends React.Component<{}, {}> {
    private clientRef: any = React.createRef();

    private sendMessage = () => {
        this.clientRef.sendMessage('/app/foo', 1);
    }

    render () {
        return <div>
                <span onClick={this.sendMessage}>Olol, test</span>
                <SockJsClient url='http://localhost:8081/ws' topics={['/topic/bar']}
                    onMessage={(msg) => { console.log(msg); }}
                    ref={ (client) => { this.clientRef = client }} />
            </div>
    }
}

