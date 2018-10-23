import * as React from 'react'
import { withStyles } from '@material-ui/core/styles';
import MenuItem from '@material-ui/core/MenuItem';
import FormControl from '@material-ui/core/FormControl';
import Select from '@material-ui/core/Select';
import InputLabel from '@material-ui/core/InputLabel';
import TextField from '@material-ui/core/TextField';
import Grid from '@material-ui/core/Grid';
import Card from '@material-ui/core/Card';

const styles = theme => ({
	formControl: {
		margin: theme.spacing.unit,
		minWidth: 120,
	},
	select: {
		minWidth: 120,
	},
    root: {
        width: "100%"
    },
});

interface IHomeState {
    dictsList: string[]
    dictsSelected: string
    dicts: {
        [key: string]: string[]
    }
    dict: string
}

class Home extends React.Component<any, IHomeState> {
    constructor(props: any) {
        super(props)
        this.state = {
            dictsSelected: "",
            dictsList: [],
            dicts: {},
            dict: ""
        }
    }

    componentWillMount() {
        let that = this;
        fetch("/api/dicts/list", { method: "get" })
            .then((res) => res.json(), (error) => console.log(error))
            .then((dictsList) => this.setState({ dictsList: dictsList }))
    }

    private handleDictFetch = name => {
        fetch("/api/dict/"+name, { method: "get" })
            .then((res) => res.json(), (error) => console.log(error))
            .then((dict) => 
                this.setState({ 
                    dicts: {...this.state.dicts, [name]: dict}, 
                    dict: dict.join(",")
                }))
    }

    private handleDictsSelectChange = event => { 
        let dictName = event.target.value 
        this.setState({ dictsSelected: dictName })
        if (!this.state.dicts[dictName]) {
            this.handleDictFetch(dictName)
            return
        } else {
            this.setState({ dict: this.state.dicts[dictName].join(",") })
        }
    }

    render () {
        const { classes } = this.props;

        let dictsOptions = this.state.dictsList.map(function(e) {
            return <MenuItem key={e} value={e}>{e}</MenuItem>
        })
        
        return <div>
            <Card><Grid container justify="space-between" spacing={32}>
                <Grid item xs={3}>
                    <FormControl classes={classes.formControl}>
                        <InputLabel>Словарь</InputLabel>
                        <Select 
                            classes={classes.select}
                            value={this.state.dictsSelected}
                            autoWidth
                            onChange={this.handleDictsSelectChange}>
                            {dictsOptions}
                        </Select>
                    </FormControl>
                </Grid>
                <Grid item xs={9}>
                    <TextField
                        label="Словарь"
                        value={this.state.dict}
                        multiline />
                </Grid>
            </Grid></Card>
        </div>
    }
}

export default withStyles(styles)(Home);
