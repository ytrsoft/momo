rpc.exports = {
  image: (id) => {
    return { id }
  },
  profile: (id) => {
    return { id }
  },
  nearly: () => {
    return {}
  },
  news: () => {
    return {}
  },
  timline: (id) => {
    return { id }
  },
  comments: (id) => {
    return { id }
  },
  post: (msg) => {
    return { msg }
  },
  receive: (handler) => {
    handler && handler()
  }
}
